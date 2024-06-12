package com.xpertcaller.server.expertdata.boimpl;


import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.beans.Pricing;
import com.xpertcaller.server.expertdata.beans.ScheduleMeeting;
import com.xpertcaller.server.expertdata.beans.response.ScheduleMeetingResponse;
import com.xpertcaller.server.expertdata.bo.ExpertDetailBo;
import com.xpertcaller.server.user.beans.user.AvailableTimeSlotChunks;
import com.xpertcaller.server.user.db.interfaces.dao.ScheduleMeetingDao;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.ScheduleMeetingEntity;
import com.xpertcaller.server.user.db.interfaces.dao.AvailableTimeSlotDao;
import com.xpertcaller.server.user.db.interfaces.dao.UserDao;
import com.xpertcaller.server.user.db.interfaces.dao.UserProfileDao;
import com.xpertcaller.server.user.db.sql.entities.UserEntity;
import com.xpertcaller.server.user.db.sql.entities.profileEntities.*;
import com.xpertcaller.server.user.service.interfaces.UserService;
import com.xpertcaller.server.user.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class ExpertDetailBoImpl implements ExpertDetailBo {

    @Autowired
    UserDao userDao;

    @Autowired
    UserProfileDao userProfileDao;

    @Autowired
    AvailableTimeSlotDao availableTimeSlotDao;

    @Autowired
    ScheduleMeetingDao scheduleMeetingDao;

    @Autowired
    UserService userService;

    @Override
    public ExpertDetails fetchExpertDetails(String id){
        UserEntity userEntity = userDao.getUserById(id);
        return getExpertDetailByUser(userEntity);
    }

    private ExpertDetails getExpertDetailByUser(UserEntity userEntity){
        UserProfileEntity userProfileEntity = userProfileDao.getProfileByUser(userEntity);

        ExpertDetails expertDetails = new ExpertDetails();

        expertDetails.setId(userEntity.getUserId());
        expertDetails.setAbout(userEntity.getAbout());
        expertDetails.setGender(userEntity.getGender());
        expertDetails.setName(userEntity.getName());
        expertDetails.setCity(userEntity.getAddressEntity()!=null ? userEntity.getAddressEntity().getCity() : null);
        if(userProfileEntity != null) {
            expertDetails.setCategory(userProfileEntity.getCategoryEntity() != null ? userProfileEntity.getCategoryEntity().getCategory() : null);
            expertDetails.setSkills(userProfileEntity.getCategoryEntity() != null ? userProfileEntity.getCategoryEntity().getSkills() : null);
            expertDetails.setFiles(userProfileEntity.getFiles());
            expertDetails.setLanguages(userProfileEntity.getLanguages());
            List<EducationDetailsEntity> educationDetailsEntityList = userProfileEntity.getEducationDetailEntities();
            List<String> degrees = new ArrayList<>();
            if(educationDetailsEntityList != null)
                educationDetailsEntityList.forEach(educationDetailsEntity -> {
                    degrees.add(educationDetailsEntity.getDegree());
                });

            expertDetails.setDegree(degrees);
            List<ExperienceEntity> experienceEntities = userProfileEntity.getExperienceEntities();

            AtomicReference<Integer> years = new AtomicReference<>((int) 0);
            AtomicReference<Integer> months = new AtomicReference<>((int) 0);
            experienceEntities.forEach(experienceEntity -> {
                years.set(years.get() + experienceEntity.getYears());
                months.set(months.get() + experienceEntity.getMonths());
            });
            float totalExperience = years.get() + (months.get()/12f);
            expertDetails.setExperience(totalExperience);

            PricingEntity pricingEntity = userProfileEntity.getPricingEntity();
            if(pricingEntity != null){
                Pricing pricing = new Pricing(pricingEntity.getVideoCalling(), pricingEntity.getAudioCalling(), pricingEntity.getMessage());
                expertDetails.setPricing(pricing);

            }
        }

        return expertDetails;
    }

    /**
     * Fetch all the experts in the system
     * @return List of ExpertDetails
     */
    @Override
    public List<ExpertDetails> fetchAllExpertDetails(){
        List<UserEntity> userEntityList = userDao.getAllUsers();
        List<ExpertDetails> expertDetails = new ArrayList<>();
        userEntityList.forEach(userEntity -> {
            expertDetails.add(getExpertDetailByUser(userEntity));
        });
        return expertDetails;
    }

    /**
     * fetch Time slots by user id
     * @param userId
     * @param startDateL
     * @param zone
     * @return List of ExpertAvailableTimeSlots
     */
    @Override
    public List<ExpertAvailableTimeSlots> fetchTimeSlotByUser(String userId, long startDateL, String zone){
        long twentyThreeHoursInMillis = 23 * 60 * 60 * 1000L;
        long fiftyNineMinutesInMillis = 59 * 60 * 1000L;
        long endDateL = startDateL + twentyThreeHoursInMillis + fiftyNineMinutesInMillis;

        Date startDate = new Date(startDateL);
        Date endDate = new Date(endDateL);

        List<AvailableTimeSlotEntity> availableTimeSlotEntityList = availableTimeSlotDao.getTimeSlotsOfStartDateInBetween(userId, startDate, endDate);
        List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities = new ArrayList<>();
        availableTimeSlotEntityList.forEach(availableTimeSlotEntity -> {
            availableTimeSlotChunksEntities.addAll(availableTimeSlotDao.getAvailableTimeslotChunksByAvailableTimeSlot(availableTimeSlotEntity.getTimeSlotId()));
        });

        return convertAvailableTimeSlotChunkEntityToExpertAvailableTimeSlots(availableTimeSlotChunksEntities);
    }

    private List<ExpertAvailableTimeSlots> convertAvailableTimeSlotChunkEntityToExpertAvailableTimeSlots(List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities){
        List<ExpertAvailableTimeSlots> availableTimeSlotChunks = new ArrayList<>();

        availableTimeSlotChunksEntities.forEach(availableTimeSlotChunksEntity -> {
            availableTimeSlotChunks.add(new ExpertAvailableTimeSlots(availableTimeSlotChunksEntity.getTimeSlotChunkId(),
                    availableTimeSlotChunksEntity.getStartTime().getTime(), availableTimeSlotChunksEntity.getEndTime().getTime(),
                    availableTimeSlotChunksEntity.getZone(), availableTimeSlotChunksEntity.getStatus()));
        });

        return availableTimeSlotChunks;
    }

    /**
     * get all the schedule meetings by user id
     * @return List of ScheduleMeetingResponse
     * @throws BusinessException
     */
    @Override
    public List<ScheduleMeetingResponse> getAllScheduleMeetingsBySubscriber() throws BusinessException {
        String userId = CommonUtil.getCurrentUser().getUserId();
        List<ScheduleMeetingResponse> scheduleMeetingResponses = new ArrayList<>();
        List<ScheduleMeetingEntity> scheduleMeetingEntities = scheduleMeetingDao.getAllScheduleMeetingsBySubscriber(userId);
        scheduleMeetingEntities.forEach(scheduleMeetingEntity -> {
            scheduleMeetingResponses.add(convertScheduleMeetingEntityToScheduleMeeting(scheduleMeetingEntity));
        });
        return scheduleMeetingResponses;
    }

    /**
     * get all the schedule meetings by publisher
     * @return List of ScheduleMeetingResponse
     * @throws BusinessException
     */
    @Override
    public List<ScheduleMeetingResponse> getAllScheduleMeetingsByPublisher() throws BusinessException {
        String userId = CommonUtil.getCurrentUser().getUserId();
        List<ScheduleMeetingResponse> scheduleMeetingResponses = new ArrayList<>();
        List<ScheduleMeetingEntity> scheduleMeetingEntities = scheduleMeetingDao.getAllScheduleMeetingsByPublisher(userId);
        scheduleMeetingEntities.forEach(scheduleMeetingEntity -> {
            scheduleMeetingResponses.add(convertScheduleMeetingEntityToScheduleMeeting(scheduleMeetingEntity));
        });
        return scheduleMeetingResponses;
    }

    /**
     * add schedule meeting
     * @param scheduleMeeting
     * @return ScheduleMeetingResponse
     * @throws BusinessException
     */
    @Override
    public ScheduleMeetingResponse addScheduleMeeting(ScheduleMeeting scheduleMeeting) throws BusinessException {
        String userId = CommonUtil.getCurrentUser().getUserId();
        ScheduleMeetingEntity scheduleMeetingEntity = convertScheduleMeetingToScheduleMeetingEntity(scheduleMeeting);
        scheduleMeetingEntity.setSubscriber(userId);
        scheduleMeetingEntity.setBookingId(UUID.randomUUID().toString());
        String timeSlotId = scheduleMeeting.getTimeSlotId();
        AvailableTimeSlotChunksEntity availableTimeSlotChunksEntity = availableTimeSlotDao.getAvailableTimeSlotChunksEntityById(timeSlotId);
        long startTime = availableTimeSlotChunksEntity.getStartTime().getTime();
        long duration = (scheduleMeeting.getDuration() - 1) * 60 * 1000L;
        long endDateL = startTime + duration;
        List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities = availableTimeSlotDao.getAvailableTimeSlotChunksByStartTimeBetween(new Date(startTime), new Date(endDateL));

        availableTimeSlotChunksEntities.forEach(modifyAvailableTimeSlotChunksEntity -> {
            modifyAvailableTimeSlotChunksEntity.setStatus(scheduleMeeting.getStatus());
            modifyAvailableTimeSlotChunksEntity.setScheduleMeetingEntity(scheduleMeetingEntity);
        });
        scheduleMeetingEntity.setAvailableTimeSlotChunksEntities(availableTimeSlotChunksEntities);
        return convertScheduleMeetingEntityToScheduleMeeting(scheduleMeetingDao.saveScheduleMeeting(scheduleMeetingEntity));
    }

    /**
     * update schedule meeting
     * @param meetingId
     * @param status
     * @return ScheduleMeetingResponse
     * @throws BusinessException
     */
    @Override
    public ScheduleMeetingResponse updateStatusOfMeeting(String meetingId, int status) throws BusinessException {
        ScheduleMeetingEntity scheduleMeetingEntity = scheduleMeetingDao.getScheduleMeetingById(meetingId);
        List<String> timeSlotIds = scheduleMeetingEntity.getTimeSlotIds();
        if(timeSlotIds != null){
            for (String timeSlotId : timeSlotIds) {
                userService.updateAvailableTimeslotChunkStatus(timeSlotId, status);
            }
        }
        scheduleMeetingEntity.setStatus(status);
        return convertScheduleMeetingEntityToScheduleMeeting(scheduleMeetingDao.saveScheduleMeeting(scheduleMeetingEntity));
    }

    private ScheduleMeetingEntity convertScheduleMeetingToScheduleMeetingEntity(ScheduleMeeting scheduleMeeting) {
        ScheduleMeetingEntity scheduleMeetingEntity = new ScheduleMeetingEntity();
        scheduleMeetingEntity.setPublisher(scheduleMeeting.getPublisher());
        scheduleMeetingEntity.setSubscriber(scheduleMeeting.getSubscriber());
        //scheduleMeetingEntity.setTimeSlotIds(scheduleMeeting.getTimeSlotIds());
        scheduleMeetingEntity.setStatus(scheduleMeeting.getStatus());
        scheduleMeetingEntity.setMode(scheduleMeeting.getMode());

        return scheduleMeetingEntity;
    }

    private ScheduleMeetingResponse convertScheduleMeetingEntityToScheduleMeeting(ScheduleMeetingEntity scheduleMeetingEntity) {
        ScheduleMeetingResponse scheduleMeetingResponse = new ScheduleMeetingResponse();
        scheduleMeetingResponse.setPublisher(scheduleMeetingEntity.getPublisher());
        scheduleMeetingResponse.setSubscriber(scheduleMeetingEntity.getSubscriber());
        scheduleMeetingResponse.setTimeSlotIds(scheduleMeetingEntity.getTimeSlotIds());
        scheduleMeetingResponse.setStatus(scheduleMeetingEntity.getStatus());
        scheduleMeetingResponse.setMode(scheduleMeetingEntity.getMode());
        scheduleMeetingResponse.setBookingId(scheduleMeetingEntity.getBookingId());
        List<AvailableTimeSlotChunks> availableTimeSlotChunksList = new ArrayList<>();

        List<AvailableTimeSlotChunksEntity> availableTimeSlotChunksEntities = scheduleMeetingEntity.getAvailableTimeSlotChunksEntities();

        availableTimeSlotChunksEntities.forEach(availableTimeSlotChunksEntity -> {
            AvailableTimeSlotChunks availableTimeSlotChunk = new AvailableTimeSlotChunks();
            availableTimeSlotChunk.setId(availableTimeSlotChunksEntity.getTimeSlotChunkId());
            availableTimeSlotChunk.setStartTime(availableTimeSlotChunksEntity.getStartTime().getTime());
            availableTimeSlotChunk.setEndTime(availableTimeSlotChunksEntity.getEndTime().getTime());
            availableTimeSlotChunk.setZone(availableTimeSlotChunksEntity.getZone());
            availableTimeSlotChunk.setStatus(availableTimeSlotChunksEntity.getStatus());
            availableTimeSlotChunksList.add(availableTimeSlotChunk);
        });
        scheduleMeetingResponse.setTimeSlots(availableTimeSlotChunksList);
        return scheduleMeetingResponse;
    }
}

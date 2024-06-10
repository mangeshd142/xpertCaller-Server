package com.xpertcaller.server.expertdata.boimpl;


import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.expertdata.beans.ExpertAvailableTimeSlots;
import com.xpertcaller.server.expertdata.beans.ExpertDetails;
import com.xpertcaller.server.expertdata.beans.Pricing;
import com.xpertcaller.server.expertdata.beans.ScheduleMeeting;
import com.xpertcaller.server.expertdata.bo.ExpertDetailBo;
import com.xpertcaller.server.expertdata.db.interfaces.ScheduleMeetingDao;
import com.xpertcaller.server.expertdata.db.sql.entities.ScheduleMeetingEntity;
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

    @Override
    public List<ExpertDetails> fetchAllExpertDetails(){
        List<UserEntity> userEntityList = userDao.getAllUsers();
        List<ExpertDetails> expertDetails = new ArrayList<>();
        userEntityList.forEach(userEntity -> {
            expertDetails.add(getExpertDetailByUser(userEntity));
        });
        return expertDetails;
    }

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

    @Override
    public List<ScheduleMeeting> getAllScheduleMeetingsBySubscriber() throws BusinessException {
        String userId = CommonUtil.getCurrentUser().getUserId();
        List<ScheduleMeeting> scheduleMeetings = new ArrayList<>();
        List<ScheduleMeetingEntity> scheduleMeetingEntities = scheduleMeetingDao.getAllScheduleMeetingsBySubscriber(userId);
        scheduleMeetingEntities.forEach(scheduleMeetingEntity -> {
            scheduleMeetings.add(convertScheduleMeetingEntityToScheduleMeeting(scheduleMeetingEntity));
        });
        return scheduleMeetings;
    }

    @Override
    public List<ScheduleMeeting> getAllScheduleMeetingsByPublisher() throws BusinessException {
        String userId = CommonUtil.getCurrentUser().getUserId();
        List<ScheduleMeeting> scheduleMeetings = new ArrayList<>();
        List<ScheduleMeetingEntity> scheduleMeetingEntities = scheduleMeetingDao.getAllScheduleMeetingsByPublisher(userId);
        scheduleMeetingEntities.forEach(scheduleMeetingEntity -> {
            scheduleMeetings.add(convertScheduleMeetingEntityToScheduleMeeting(scheduleMeetingEntity));
        });
        return scheduleMeetings;
    }

    @Override
    public ScheduleMeeting addScheduleMeeting(ScheduleMeeting scheduleMeeting) throws BusinessException {
        String userId = CommonUtil.getCurrentUser().getUserId();
        ScheduleMeetingEntity scheduleMeetingEntity = convertScheduleMeetingToScheduleMeetingEntity(scheduleMeeting);
        scheduleMeetingEntity.setSubscriber(userId);
        List<String> timeSlotIds = scheduleMeetingEntity.getTimeSlotIds();
        int status = scheduleMeeting.getStatus();
        scheduleMeetingEntity.setBookingId(UUID.randomUUID().toString());
        if(timeSlotIds != null){
            for (String timeSlotId : timeSlotIds) {
                userService.updateAvailableTimeslotChunkStatus(timeSlotId, status);
            }
        }
        return convertScheduleMeetingEntityToScheduleMeeting(scheduleMeetingDao.saveScheduleMeeting(scheduleMeetingEntity));
    }

    @Override
    public ScheduleMeeting updateStatusOfMeeting(String meetingId, int status) throws BusinessException {
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
        scheduleMeetingEntity.setTimeSlotIds(scheduleMeeting.getTimeSlotIds());
        scheduleMeetingEntity.setStatus(scheduleMeeting.getStatus());
        scheduleMeetingEntity.setMode(scheduleMeeting.getMode());

        return scheduleMeetingEntity;
    }

    private ScheduleMeeting convertScheduleMeetingEntityToScheduleMeeting(ScheduleMeetingEntity scheduleMeetingEntity) {
        ScheduleMeeting scheduleMeeting = new ScheduleMeeting();
        scheduleMeeting.setPublisher(scheduleMeetingEntity.getPublisher());
        scheduleMeeting.setSubscriber(scheduleMeetingEntity.getSubscriber());
        scheduleMeeting.setTimeSlotIds(scheduleMeetingEntity.getTimeSlotIds());
        scheduleMeeting.setStatus(scheduleMeetingEntity.getStatus());
        scheduleMeeting.setMode(scheduleMeetingEntity.getMode());
        scheduleMeeting.setBookingId(scheduleMeetingEntity.getBookingId());

        return scheduleMeeting;
    }
}

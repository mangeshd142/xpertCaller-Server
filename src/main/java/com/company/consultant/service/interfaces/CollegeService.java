package com.company.consultant.service.interfaces;

import com.company.consultant.moduls.College;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface CollegeService {
    List<College> insertColleges() throws IOException, JSONException;
    List<College> getColleges(String collegeName);
}

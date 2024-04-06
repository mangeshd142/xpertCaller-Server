package com.xpertcaller.server.user.service.interfaces;

import com.xpertcaller.server.user.beans.user.College;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface CollegeService {
    List<College> insertColleges() throws IOException, JSONException;
    List<College> getColleges(String collegeName);
}

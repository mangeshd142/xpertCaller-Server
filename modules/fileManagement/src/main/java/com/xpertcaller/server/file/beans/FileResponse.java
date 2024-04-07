package com.xpertcaller.server.file.beans;

import lombok.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileResponse {
    private List<String> files;
}

package com.example.demo.util;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ResponseObject<T> {
    public final static int FILE_NOT_UPLOADED_CODE = HttpStatus.UNPROCESSABLE_ENTITY.value();
    public final static int NO_FILE_SELECTED_CODE = HttpStatus.BAD_REQUEST.value();
    public final static int FILE_UPLOADED_CODE = HttpStatus.OK.value();

    private final static String FILE_NOT_UPLOADED = "El archivo no se cargó correctamente";
    private final static String NO_FILE_SELECTED = "Seleccione un archivo primero!";
    private final static String FILE_UPLOADED = "El archivo ha subido correctamente";

    public static final Map<Integer, String> messageMap;

    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(FILE_NOT_UPLOADED_CODE, FILE_NOT_UPLOADED);
        map.put(FILE_UPLOADED_CODE, FILE_UPLOADED);
        map.put(NO_FILE_SELECTED_CODE, NO_FILE_SELECTED);
        messageMap = Collections.unmodifiableMap(map);
    }

    private int status;
    private T data;
}

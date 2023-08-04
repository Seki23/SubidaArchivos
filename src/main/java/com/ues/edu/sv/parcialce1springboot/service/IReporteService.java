package com.ues.edu.sv.parcialce1springboot.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IReporteService<T> {

    void generarReporte(InputStream stream, HttpServletResponse response, List<T> data)throws IOException;



}


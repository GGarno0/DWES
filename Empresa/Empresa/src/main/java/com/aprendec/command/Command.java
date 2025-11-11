package com.aprendec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void ejecutar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

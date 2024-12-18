package com.nfc;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
public class Nfc_Application {

	public static void main(String[] args) {
		SpringApplication.run(Nfc_Application.class, args);
	}
	
	@Order(-1)
    @Component
    public static class SimpleCORSFilter implements Filter {

        @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
                throws IOException, ServletException {

            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            // Setting CORS headers
            response.setHeader("Access-Control-Allow-Origin", "*"); // Allow all domains
            response.setHeader("Access-Control-Allow-Credentials", "true"); // Allow credentials
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600"); // Cache duration for preflight requests
            response.setHeader("Access-Control-Allow-Headers", "*"); // Allow all headers

            chain.doFilter(req, res);
        }
    }

}

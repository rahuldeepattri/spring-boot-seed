package com.kawnayeen.logger.service;

import com.kawnayeen.logger.model.entity.Log;
import com.kawnayeen.logger.repository.LogRepository;
import com.kawnayeen.logger.service.exception.InvalidLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Service
public class LogServiceBean implements LogService{
    @Autowired
    private LogRepository logRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Log create(Log log) {
        if(log.getId()!=null)
            throw new InvalidLogException("cannot preset log id");
        try {
            return logRepository.save(log);
        }catch (Exception e){
            throw new InvalidLogException();
        }
    }
}

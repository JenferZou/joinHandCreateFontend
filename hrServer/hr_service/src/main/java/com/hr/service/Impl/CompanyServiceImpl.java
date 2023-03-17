package com.hr.service.Impl;


import com.hr.mapper.CompanyMapper;
import com.hr.model.Company;
import com.hr.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> QueryFourCompany() {
        return companyMapper.QueryFourCompany();
    }
}

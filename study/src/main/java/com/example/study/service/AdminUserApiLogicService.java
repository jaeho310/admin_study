package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserApiRequest;
import com.example.study.model.network.response.AdminUserApiResponse;
import com.example.study.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {


    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .lastLoginAt(body.getLastLoginAt())
                .loginFailCount(body.getLoginFailCount())
                .registeredAt(body.getRegisteredAt())
                .unregisteredAt(body.getUnregisteredAt())
                .build();

        AdminUser newAdminUser = baseRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(adminUser -> {
                    adminUser.setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setLastLoginAt(body.getLastLoginAt())
                            .setLoginFailCount(body.getLoginFailCount())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());
                    return adminUser;
                })
                .map(newAdminUser -> baseRepository.save(newAdminUser))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(() ->Header.ERROR("데이터 없음"));
    }

    private Header<AdminUserApiResponse> response(AdminUser adminuser) {

        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .account(adminuser.getAccount())
                .password(adminuser.getPassword())
                .status(adminuser.getStatus())
                .role(adminuser.getRole())
                .lastLoginAt(adminuser.getLastLoginAt())
                .loginFailCount(adminuser.getLoginFailCount())
                .registeredAt(adminuser.getRegisteredAt())
                .unregisteredAt(adminuser.getUnregisteredAt())
                .build();

        return Header.OK(adminUserApiResponse);
    }
}

package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.UserOrderInfoApiResponse;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.repository.CategoryRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {

        Partner partner = Partner.builder()
                .name(request.getData().getName())
                .status(request.getData().getStatus())
                .address(request.getData().getAddress())
                .callCenter(request.getData().getCallCenter())
                .partnerNumber(request.getData().getPartnerNumber())
                .businessNumber(request.getData().getBusinessNumber())
                .ceoName(request.getData().getCeoName())
                .registeredAt(request.getData().getRegisteredAt())
                .unregisteredAt(request.getData().getUnregisteredAt())
                .category(categoryRepository.getOne(request.getData().getCategoryId()))
                .build();

        Partner newPartner = baseRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        return baseRepository.findById(request.getData().getId())
                .map(partner -> {
                    partner.setName(request.getData().getName())
                            .setStatus(request.getData().getStatus())
                            .setAddress(request.getData().getAddress())
                            .setCallCenter(request.getData().getCallCenter())
                            .setPartnerNumber(request.getData().getPartnerNumber())
                            .setBusinessNumber(request.getData().getBusinessNumber())
                            .setCeoName(request.getData().getCeoName())
                            .setRegisteredAt(request.getData().getRegisteredAt())
                            .setUnregisteredAt(request.getData().getUnregisteredAt())
                            .setCategory(categoryRepository.getOne(request.getData().getCategoryId()));

                    return partner;
                })
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                        return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    public Header<PartnerApiResponse> response(Partner partner) {
        PartnerApiResponse response = PartnerApiResponse.builder()
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return Header.OK(response);
    }

    @Override
    public Header<List<PartnerApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Override
    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        return null;
    }
}

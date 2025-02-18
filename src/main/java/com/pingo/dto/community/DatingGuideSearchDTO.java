package com.pingo.dto.community;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DatingGuideSearchDTO {
    private String category;
    private String sort;
    private int cateNo;
    private List<DatingGuideDTO> datingGuideList;

    public DatingGuideSearchDTO(String category, int cateNo) {
        this.category = category;
        this.sort = "popular";
        this.cateNo = cateNo;
        this.datingGuideList = new ArrayList<>();
    }

    public void addDatingGuideList(DatingGuideDTO datingGuideDTO) {
        datingGuideList.add(datingGuideDTO);
    }
}

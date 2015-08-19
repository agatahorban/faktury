package com.assen.invoices.dto;

import com.assen.invoices.entities.Goods;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arek
 */
@XmlRootElement(name = "goodsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoodsListDto {

    @XmlElement(name = "goods")
    private List<Goods> list;

    public GoodsListDto() {
        this.list = new ArrayList<>();
    }

    public GoodsListDto(List<Goods> list) {
        this.list = list;
    }
    
    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }
}

package com.ozstrategy.model.dictionary;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by lihao1 on 7/13/15.
 */
@Table(name = "t_dictionary")
@NamedQueries({
        @NamedQuery(name = "getDics",query = "select r.*,ur.dtype from t_dictionary r join t_dictionarytype ur on r.typeId=ur.id where 1=1"),
        @NamedQuery(name = "getDicsCount",query = "select count(*) from t_dictionary r join t_dictionarytype ur on r.typeId=ur.id where 1=1")

})
public class Dictionary extends BaseEntity{
    @Id
    private Long id;
    private Long typeId;
    private String keyValue;
    private Date createDate;

    public Dictionary() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Dictionary role = (Dictionary) o;
        return new EqualsBuilder()
                .append(id, role.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .hashCode();
    }
}

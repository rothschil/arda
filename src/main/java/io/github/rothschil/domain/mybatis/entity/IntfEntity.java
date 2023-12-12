package io.github.rothschil.domain.mybatis.entity;


import io.github.rothschil.common.base.entity.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntfEntity extends BasePo<Long> {

    private Long id;

    private String callee;

    private String caller;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
}

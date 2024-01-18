package io.github.rothschil.domain.jpa.entity;

import io.github.rothschil.common.persistence.jpa.entity.AbstractPo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/15 - 17:02
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "intf_log")
public class IntfLogJpa extends AbstractPo<Long> {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "caller")
    private String caller;

    @Column(name = "callee")
    private String callee;

    public IntfLogJpa() {
    }

    public IntfLogJpa(String caller, String callee) {
        this.caller = caller;
        this.callee = callee;
    }
}

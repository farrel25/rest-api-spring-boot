package practice.restapispringboot.models.entities;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * This BaseEntity class will be used as a kind of superclass
 * for each of our entities. So each entity that needs to
 * implement base log auditing can extend this BaseEntity.
 * 
 * @EntityListeners(value = AuditingEntityListener.class)
 * To make this BaseEntity class a kind of listener. Whenever
 * there is an event log such as CRUD from an entity that
 * extends this BaseEntity class, then this class will be
 * executed its properties.
 */
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<T> {
    
    @CreatedBy
    protected T createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @LastModifiedBy
    protected T updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

//    public T getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(T createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public T getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(T updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    public Date getUpdatedDate() {
//        return updatedDate;
//    }
//
//    public void setUpdatedDate(Date updatedDate) {
//        this.updatedDate = updatedDate;
//    }
}

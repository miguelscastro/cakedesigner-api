package br.com.miguelcastro.cakedesigner_api.modules.order.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "addresses")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cd_address")
    private UUID id;

    @Column(name = "cd_cep", nullable = false)
    private String cep;

    @Column(name = "nm_street", nullable = false)
    private String street;

    @Column(name = "nr_number", nullable = false)
    private String number;

    @Column(name = "ds_full_address")
    private String fullAddress;

    @Column(name = "nm_neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "nm_city", nullable = false)
    private String city;

    @Column(name = "sg_state", nullable = false)
    private String state;

    @CreationTimestamp
    @Column(name = "dt_creation", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime updatedAt;
}

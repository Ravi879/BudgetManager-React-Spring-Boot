package com.budget.manager.repository.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "income")
@SequenceGenerator(name="item_sequence_generator", sequenceName="income_sequence")
public class IncomeEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 8483374825350563835L;

}

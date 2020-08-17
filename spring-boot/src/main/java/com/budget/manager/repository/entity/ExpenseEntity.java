package com.budget.manager.repository.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "expense")
@SequenceGenerator(name="item_sequence_generator", sequenceName="expense_sequence")
public class ExpenseEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 8920572149328485309L;


}

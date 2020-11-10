//package com.baec.antiviral.lib.server.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
* ${table.tableComment}
*/
@Entity
@Data
@Table(name = "${table.tableName}")
public class ${table.className} {

    <#list fieldList as field>
        /**
        * ${field.comment!''}
        */
        <#if field.pk>
        @Id
        </#if>
        @Column(name = "${field.columnName}")
        <#if field.pk>
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        </#if>
        private ${field.javaType!''} ${field.name!''};
    </#list>
}

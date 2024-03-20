/*
 * This file is generated by jOOQ.
 */
package com.devacademy.discussionforum.jooq;


import com.devacademy.discussionforum.jooq.tables.Messages;
import com.devacademy.discussionforum.jooq.tables.Topics;
import com.devacademy.discussionforum.jooq.tables.Users;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.messages</code>.
     */
    public final Messages MESSAGES = Messages.MESSAGES;

    /**
     * The table <code>public.topics</code>.
     */
    public final Topics TOPICS = Topics.TOPICS;

    /**
     * The table <code>public.users</code>.
     */
    public final Users USERS = Users.USERS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Messages.MESSAGES,
            Topics.TOPICS,
            Users.USERS
        );
    }
}
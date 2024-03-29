/*
 * This file is generated by jOOQ.
 */
package com.devacademy.discussionforum.jooq;


import com.devacademy.discussionforum.jooq.tables.ForumUser;
import com.devacademy.discussionforum.jooq.tables.Message;
import com.devacademy.discussionforum.jooq.tables.Topic;

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
     * The table <code>public.forum_user</code>.
     */
    public final ForumUser FORUM_USER = ForumUser.FORUM_USER;

    /**
     * The table <code>public.message</code>.
     */
    public final Message MESSAGE = Message.MESSAGE;

    /**
     * The table <code>public.topic</code>.
     */
    public final Topic TOPIC = Topic.TOPIC;

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
            ForumUser.FORUM_USER,
            Message.MESSAGE,
            Topic.TOPIC
        );
    }
}

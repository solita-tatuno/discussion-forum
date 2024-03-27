/*
 * This file is generated by jOOQ.
 */
package com.devacademy.discussionforum.jooq.tables;


import com.devacademy.discussionforum.jooq.Keys;
import com.devacademy.discussionforum.jooq.Public;
import com.devacademy.discussionforum.jooq.tables.Message.MessagePath;
import com.devacademy.discussionforum.jooq.tables.Topic.TopicPath;
import com.devacademy.discussionforum.jooq.tables.records.ForumUserRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ForumUser extends TableImpl<ForumUserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.forum_user</code>
     */
    public static final ForumUser FORUM_USER = new ForumUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ForumUserRecord> getRecordType() {
        return ForumUserRecord.class;
    }

    /**
     * The column <code>public.forum_user.id</code>.
     */
    public final TableField<ForumUserRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.forum_user.username</code>.
     */
    public final TableField<ForumUserRecord, String> USERNAME = createField(DSL.name("username"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.forum_user.password_hash</code>.
     */
    public final TableField<ForumUserRecord, String> PASSWORD_HASH = createField(DSL.name("password_hash"), SQLDataType.VARCHAR(60).nullable(false), this, "");

    /**
     * The column <code>public.forum_user.is_admin</code>.
     */
    public final TableField<ForumUserRecord, Boolean> IS_ADMIN = createField(DSL.name("is_admin"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private ForumUser(Name alias, Table<ForumUserRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private ForumUser(Name alias, Table<ForumUserRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.forum_user</code> table reference
     */
    public ForumUser(String alias) {
        this(DSL.name(alias), FORUM_USER);
    }

    /**
     * Create an aliased <code>public.forum_user</code> table reference
     */
    public ForumUser(Name alias) {
        this(alias, FORUM_USER);
    }

    /**
     * Create a <code>public.forum_user</code> table reference
     */
    public ForumUser() {
        this(DSL.name("forum_user"), null);
    }

    public <O extends Record> ForumUser(Table<O> path, ForeignKey<O, ForumUserRecord> childPath, InverseForeignKey<O, ForumUserRecord> parentPath) {
        super(path, childPath, parentPath, FORUM_USER);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ForumUserPath extends ForumUser implements Path<ForumUserRecord> {
        public <O extends Record> ForumUserPath(Table<O> path, ForeignKey<O, ForumUserRecord> childPath, InverseForeignKey<O, ForumUserRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ForumUserPath(Name alias, Table<ForumUserRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ForumUserPath as(String alias) {
            return new ForumUserPath(DSL.name(alias), this);
        }

        @Override
        public ForumUserPath as(Name alias) {
            return new ForumUserPath(alias, this);
        }

        @Override
        public ForumUserPath as(Table<?> alias) {
            return new ForumUserPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<ForumUserRecord, Integer> getIdentity() {
        return (Identity<ForumUserRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ForumUserRecord> getPrimaryKey() {
        return Keys.FORUM_USER_PKEY;
    }

    @Override
    public List<UniqueKey<ForumUserRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.FORUM_USER_USERNAME_KEY);
    }

    private transient MessagePath _message;

    /**
     * Get the implicit to-many join path to the <code>public.message</code>
     * table
     */
    public MessagePath message() {
        if (_message == null)
            _message = new MessagePath(this, null, Keys.MESSAGE__MESSAGE_USER_ID_FKEY.getInverseKey());

        return _message;
    }

    private transient TopicPath _topic;

    /**
     * Get the implicit to-many join path to the <code>public.topic</code> table
     */
    public TopicPath topic() {
        if (_topic == null)
            _topic = new TopicPath(this, null, Keys.TOPIC__TOPIC_USER_ID_FKEY.getInverseKey());

        return _topic;
    }

    @Override
    public ForumUser as(String alias) {
        return new ForumUser(DSL.name(alias), this);
    }

    @Override
    public ForumUser as(Name alias) {
        return new ForumUser(alias, this);
    }

    @Override
    public ForumUser as(Table<?> alias) {
        return new ForumUser(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ForumUser rename(String name) {
        return new ForumUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ForumUser rename(Name name) {
        return new ForumUser(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ForumUser rename(Table<?> name) {
        return new ForumUser(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ForumUser where(Condition condition) {
        return new ForumUser(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ForumUser where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ForumUser where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ForumUser where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ForumUser where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ForumUser where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ForumUser where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public ForumUser where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ForumUser whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public ForumUser whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}

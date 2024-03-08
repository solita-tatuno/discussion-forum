CREATE
OR REPLACE FUNCTION update_modified_row_updated_at()
RETURNS TRIGGER AS $$
BEGIN
NEW.updated_at
= CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER update_topics_updated_at
    BEFORE UPDATE
    ON topics
    FOR EACH ROW
    EXECUTE FUNCTION update_modified_row_updated_at();
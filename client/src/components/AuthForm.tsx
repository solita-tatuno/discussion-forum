import { ErrorMessage, Field, Form, Formik } from "formik";
import { UserCredentials } from "../types";
import { UseMutateFunction } from "@tanstack/react-query";

interface Props {
  handleSubmit: UseMutateFunction<unknown, Error, UserCredentials, unknown>;
}

function AuthForm({ handleSubmit }: Props) {
  const initialValues: UserCredentials = {
    username: "",
    password: "",
  };

  return (
    <Formik
      initialValues={initialValues}
      onSubmit={(values) => handleSubmit(values)}
      validate={(values) => {
        const errors: Partial<UserCredentials> = {};
        if (!values.username) {
          errors.username = "Username required";
        }
        if (!values.password) {
          errors.password = "Password required";
        }
        return errors;
      }}
    >
      {({}) => (
        <Form className="flex flex-col gap-2">
          <Field
            className="border-2 border-black"
            type="text"
            name="username"
            placeholder="username"
          />
          <ErrorMessage
            name="username"
            className="text-red-600"
            component="span"
          />
          <Field
            className="border-2 border-black"
            type="password"
            name="password"
            placeholder="password"
          />
          <ErrorMessage
            name="password"
            className="text-red-600"
            component="span"
          />
          <button className="rounded-md bg-green-600 p-2" type="submit">
            Submit
          </button>
        </Form>
      )}
    </Formik>
  );
}

export default AuthForm;

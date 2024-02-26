import { ErrorMessage, Field, Form, Formik } from "formik";
import { UserCredentials } from "../types";
import useSignup from "../hooks/useSignup.ts";

function SignupForm() {
  const { signup, error } = useSignup();

  const initialValues: UserCredentials = {
    username: "",
    password: "",
  };

  return (
    <section className="flex flex-1 justify-center items-center">
      <div className="flex flex-col basis-1/3 gap-2">
        <h1>Sign up</h1>
        <Formik
          initialValues={initialValues}
          onSubmit={(values) => signup(values)}
        >
          {({}) => (
            <Form className="flex flex-col gap-2">
              <Field className="border-2 border-black" type="text" name="username" placeholder="username" />
              <ErrorMessage name="email" component="div" />
              <Field className="border-2 border-black" type="password" name="password" placeholder="password" />
              <ErrorMessage name="password" component="div" />
              {error &&
                <div>
                  <span className="text-red-600">{error.message} </span>
                </div>
              }
              <button className="bg-green-600 p-2 rounded-md" type="submit">
                Submit
              </button>
            </Form>
          )}
        </Formik>
      </div>
    </section>
  );
}

export default SignupForm;
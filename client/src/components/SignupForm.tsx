import { ErrorMessage, Field, Form, Formik } from "formik";

interface InitialValues {
  username: string;
  password: string;
}

function SignupForm() {
  const initialValues: InitialValues = {
    username: "",
    password: "",
  };
  return (
    <section className="flex flex-1 justify-center items-center">
      <div className="flex flex-col gap-2">
        <h1>Sign up</h1>
        <Formik
          initialValues={initialValues}
          onSubmit={(values, { setSubmitting }) => {
            setTimeout(() => {
              alert(JSON.stringify(values, null, 2));
              setSubmitting(false);
            }, 400);
          }}
        >
          {({ isSubmitting }) => (
            <Form className="flex flex-col gap-2">
              <Field className="border-2 border-black" type="text" name="username" placeholder="username" />
              <ErrorMessage name="email" component="div" />
              <Field className="border-2 border-black" type="password" name="password" placeholder="password" />
              <ErrorMessage name="password" component="div" />
              <button className="bg-green-600 p-2 rounded-md" type="submit" disabled={isSubmitting}>
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
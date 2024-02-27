import useSignup from "../hooks/useSignup.ts";
import AuthForm from "../components/AuthForm.tsx";

function Signup() {
  const { signup, error } = useSignup();
  return (
    <section className="flex flex-1 items-center justify-center">
      <div className="flex flex-col basis-1/3 gap-2">
        <h1>Signup</h1>
        <AuthForm handleSubmit={signup} error={error} />
        <div className="self-center">
          <span>Already have an account ? <a href="/login">Login</a></span>
        </div>
      </div>
    </section>
  );
}

export default Signup;
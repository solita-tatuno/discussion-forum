import useLogin from "../hooks/useLogin.ts";
import AuthForm from "../components/AuthForm.tsx";

function Login() {
  const { login, error } = useLogin();
  return (
    <section className="flex flex-1 items-center justify-center">
      <div className="flex flex-col basis-1/3 gap-2">
        <h1>Login</h1>
        <AuthForm handleSubmit={login} error={error} />
        <div className="self-center">
          <span>Don't have an account ? <a href="/">Sign up</a></span>
        </div>
      </div>
    </section>
  );
}

export default Login;
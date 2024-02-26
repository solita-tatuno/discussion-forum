export interface UserCredentials {
  username: string;
  password: string;
}

export interface ApiError {
  message: string;
}

export interface User {
  id: number;
  username: string;
  isAdmin: boolean;
}
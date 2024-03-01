export interface UserCredentials {
  username: string;
  password: string;
}

export interface User {
  id: number;
  username: string;
  isAdmin: boolean;
}

export interface Topic {
  id: number;
  name: string;
  // created at.. updated at..
  user: User;
}
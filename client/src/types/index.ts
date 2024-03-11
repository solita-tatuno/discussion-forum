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
  user: User;
  createdAt: string;
}

export type TopicUpdate = Pick<Topic, "id" | "name">

export interface Message {
  id: number;
  message: string;
  topicId: number;
  user: User;
  createdAt: string;
}

export interface SingleTopic extends Topic {
  messages: Message[];
}
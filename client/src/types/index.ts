export interface UserCredentials {
  username: string;
  password: string;
}

export interface User {
  id: number;
  username: string;
  isAdmin: boolean;
}

export interface PageableTopics {
  topics: Topic[];
  totalCount: number;
}

export interface Topic {
  id: number;
  name: string;
  user: User;
  createdAt: string;
  messageCount: number;
  lastMessageTime?: string;
}

export type TopicUpdate = Pick<Topic, "id" | "name">

export interface Message {
  id: number;
  message: string;
  topicId: number;
  user: User;
  upVotes: number;
  createdAt: string;
}

export type MessageUpdate = Pick<Message, "id" | "message" | "upVotes"> & { userId: number };

export interface SingleTopic extends Topic {
  messages: Message[];
}

export interface PaginationValues {
  page: number;
  size: number;
}
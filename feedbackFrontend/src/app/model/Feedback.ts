import {Role} from "./Role.enum";

export class Feedback {
  sender!: Role;
  receiver!: Role;
  text!: string;
  meeting!: boolean;
  anonym!: boolean;
  active!: boolean;
}

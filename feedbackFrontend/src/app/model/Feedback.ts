import {Role} from "./Role.enum";

export class Feedback {
  id: string = "";
  sender!: Role;
  receiver!: Role;
  text!: string;
  meeting!: boolean;
  anonym!: boolean;
  active!: boolean;
}

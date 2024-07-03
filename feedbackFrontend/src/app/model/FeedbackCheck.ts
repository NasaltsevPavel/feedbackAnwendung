import {Role} from "./Role.enum";

export class FeedbackCheck {
  from!: Role;
  to!: Role;
  text: string = "";
  aiGenerated: boolean = false;
  message: string = "";
}

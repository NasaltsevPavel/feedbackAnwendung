export enum Role {
  MANAGER = 'MANAGER',
  DEVELOPER = 'DEVELOPER',
  PRODUCT_OWNER = 'PRODUCT_OWNER',
  SCRUM_MASTER = 'SCRUM_MASTER',
  WORKING_STUDENT = 'WORKING_STUDENT',
  TRAINEE = 'TRAINEE',
  OFFICE = 'OFFICE',
  COMPANY = 'COMPANY',
  ANONYM = 'ANONYM'
}

export namespace Role {

  export function toString(role: Role): string {
    switch (role) {
      case Role.MANAGER:
        return 'Manager';
      case Role.DEVELOPER:
        return 'Software-Entwickler';
      case Role.PRODUCT_OWNER:
        return 'Product Owner';
      case Role.SCRUM_MASTER:
        return 'Scrum Master';
      case Role.WORKING_STUDENT:
        return 'Werkstudent';
      case Role.TRAINEE:
        return 'Praktikant';
      case Role.OFFICE:
        return 'Büro';
      case Role.COMPANY:
        return 'Unternehmen';
      case Role.ANONYM:
        return 'Anonym';

    }
  }

  export function getRoleOptionsFrom() {
    return [[Role.MANAGER, toString(Role.MANAGER)],
      [Role.DEVELOPER, toString(Role.DEVELOPER)], [Role.PRODUCT_OWNER, toString(Role.PRODUCT_OWNER)],
      [Role.SCRUM_MASTER, toString(Role.SCRUM_MASTER)],
      [Role.WORKING_STUDENT, toString(Role.WORKING_STUDENT)],
      [Role.TRAINEE, toString(Role.TRAINEE)]];
  }

  export function getRoleOptionsTo() {
    return [[Role.MANAGER, toString(Role.MANAGER)],
      [Role.DEVELOPER, toString(Role.DEVELOPER)], [Role.PRODUCT_OWNER, toString(Role.PRODUCT_OWNER)],
      [Role.SCRUM_MASTER, toString(Role.SCRUM_MASTER)], [Role.WORKING_STUDENT, toString(Role.WORKING_STUDENT)],
      [Role.TRAINEE, toString(Role.TRAINEE)], [Role.OFFICE, toString(Role.OFFICE)], [Role.COMPANY, toString(Role.COMPANY)]];
  }
}

export enum Role {
  MANAGER = 'MANAGER',
  DEVELOPER = 'DEVELOPER',
  PRODUCT_OWNER = 'PRODUCT_OWNER',
  SCRUM_MASTER = 'SCRUM_MASTER',
  OFFICE = 'OFFICE',
  COMPANY = 'COMPANY'
}

export namespace Role {

  export function toString(role: Role): string {
    switch (role) {
      case Role.MANAGER:
        return 'Manager';
      case Role.DEVELOPER:
        return 'Developer';
      case Role.PRODUCT_OWNER:
        return 'PRODUCT_OWNER';
      case Role.SCRUM_MASTER:
        return 'SCRUM_MASTER';
      case Role.COMPANY:
        return 'COMPANY';
      case Role.OFFICE:
        return 'OFFICE';

    }
  }

  export function getRoleOptionsFrom() {
    return [[Role.MANAGER, toString(Role.MANAGER)],
      [Role.DEVELOPER, toString(Role.DEVELOPER)], [Role.PRODUCT_OWNER, toString(Role.PRODUCT_OWNER)],
      [Role.SCRUM_MASTER, toString(Role.SCRUM_MASTER)]];
  }

  export function getRoleOptionsTo() {
    return [[Role.MANAGER, toString(Role.MANAGER)],
      [Role.DEVELOPER, toString(Role.DEVELOPER)], [Role.PRODUCT_OWNER, toString(Role.PRODUCT_OWNER)],
      [Role.SCRUM_MASTER, toString(Role.SCRUM_MASTER)],  [Role.COMPANY, toString(Role.COMPANY)], [Role.OFFICE, toString(Role.OFFICE)]];
  }
}

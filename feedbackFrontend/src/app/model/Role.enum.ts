export enum Role {
  MANAGER = 'MANAGER',
  DEVELOPER = 'DEVELOPER'
}

export namespace Role {

  export function toString(role: Role): string {
    switch (role) {
      case Role.MANAGER:
        return 'Manager';
      case Role.DEVELOPER:
        return 'Developer';
    }
  }

  export function getRoleOptions() {
    return [[Role.MANAGER, toString(Role.MANAGER)],
      [Role.DEVELOPER, toString(Role.DEVELOPER)]];
  }
}

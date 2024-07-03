export enum AI {
  CHECK = 'Option 1',
  CREATE = 'Option 2'
}

export namespace AI {

  export function toString(role: AI): string {
    switch (role) {
      case AI.CHECK:
        return 'Check';
      case AI.CREATE:
        return 'Create';
    }
  }

  export function getAIOptions() {
    return [[AI.CHECK, toString(AI.CHECK)],
      [AI.CREATE, toString(AI.CREATE)]];
  }
}

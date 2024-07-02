export enum AI {
  CHECK = 'Option 1',
  CREATE = 'Option 2'
}

export namespace AI {

  export function toString(role: AI): string {
    switch (role) {
      case AI.CHECK:
        return 'Option 1';
      case AI.CREATE:
        return 'Option 2';
    }
  }

  export function getAIOptions() {
    return [[AI.CHECK, toString(AI.CHECK)],
      [AI.CREATE, toString(AI.CREATE)]];
  }
}

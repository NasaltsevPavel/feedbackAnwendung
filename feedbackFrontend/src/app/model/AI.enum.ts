export enum AI {
  CHECK = 'Überprüfen',
  CREATE = 'Erstellen'
}

export namespace AI {

  export function toString(role: AI): string {
    switch (role) {
      case AI.CHECK:
        return 'Überprüfen';
      case AI.CREATE:
        return 'Erstellen';
    }
  }

  export function getAIOptions() {
    return [[AI.CHECK, toString(AI.CHECK)],
      [AI.CREATE, toString(AI.CREATE)]];
  }
}

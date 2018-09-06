export interface INotification {
    id?: number;
    message?: string;
    employeeId?: number;
}

export class Notification implements INotification {
    constructor(public id?: number, public message?: string, public employeeId?: number) {}
}

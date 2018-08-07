import { IEmployee } from 'app/shared/model//employee.model';

export interface IDepartment {
    id?: number;
    name?: string;
    head?: IEmployee;
    representative?: IEmployee;
}

export class Department implements IDepartment {
    constructor(public id?: number, public name?: string, public head?: IEmployee, public representative?: IEmployee) {}
}

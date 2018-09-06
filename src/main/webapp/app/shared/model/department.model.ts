import { IEmployee } from 'app/shared/model//employee.model';

export interface IDepartment {
    id?: number;
    name?: string;
    headId?: number;
    representativeId?: number;
    locationId?: number;
    employees?: IEmployee[];
}

export class Department implements IDepartment {
    constructor(
        public id?: number,
        public name?: string,
        public headId?: number,
        public representativeId?: number,
        public locationId?: number,
        public employees?: IEmployee[]
    ) {}
}

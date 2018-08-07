import { IEmployee } from 'app/shared/model//employee.model';
import { ILocation } from 'app/shared/model//location.model';

export interface IDepartment {
    id?: number;
    name?: string;
    head?: IEmployee;
    representative?: IEmployee;
    location?: ILocation;
}

export class Department implements IDepartment {
    constructor(
        public id?: number,
        public name?: string,
        public head?: IEmployee,
        public representative?: IEmployee,
        public location?: ILocation
    ) {}
}

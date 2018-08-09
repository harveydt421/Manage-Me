import { IEmployee } from 'app/shared/model//employee.model';

export interface IAsset {
    id?: number;
    name?: string;
    value?: number;
    employee?: IEmployee;
}

export class Asset implements IAsset {
    constructor(public id?: number, public name?: string, public value?: number, public employee?: IEmployee) {}
}

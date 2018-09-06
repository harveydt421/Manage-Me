export interface IAsset {
    id?: number;
    name?: string;
    value?: number;
    employeeId?: number;
    employeeName?: string;
}

export class Asset implements IAsset {
    constructor(
        public id?: number,
        public name?: string,
        public value?: number,
        public employeeId?: number,
        public employeeName?: string
    ) {}
}

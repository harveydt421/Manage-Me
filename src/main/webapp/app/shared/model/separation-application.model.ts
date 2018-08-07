import { Moment } from 'moment';
import { IEmployee } from 'app/shared/model//employee.model';
import { ILineItem } from 'app/shared/model//line-item.model';

export interface ISeparationApplication {
    id?: number;
    dateOfLeaving?: Moment;
    dateOfSubmission?: Moment;
    reasonForLeaving?: string;
    employee?: IEmployee;
    lineItem?: ILineItem;
}

export class SeparationApplication implements ISeparationApplication {
    constructor(
        public id?: number,
        public dateOfLeaving?: Moment,
        public dateOfSubmission?: Moment,
        public reasonForLeaving?: string,
        public employee?: IEmployee,
        public lineItem?: ILineItem
    ) {}
}

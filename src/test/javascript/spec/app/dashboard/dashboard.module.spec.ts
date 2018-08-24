import { ManageMeDashboardModule } from '../../../../../main/webapp/app/dashboard/dashboard.module';

describe('ManageMeDashboardModule', () => {
    let dashboardModule: ManageMeDashboardModule;

    beforeEach(() => {
        dashboardModule = new ManageMeDashboardModule();
    });

    it('should create an instance', () => {
        expect(dashboardModule).toBeTruthy();
    });
});

import { TasklistFrontendPage } from './app.po';

describe('tasklist-frontend App', () => {
  let page: TasklistFrontendPage;

  beforeEach(() => {
    page = new TasklistFrontendPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});

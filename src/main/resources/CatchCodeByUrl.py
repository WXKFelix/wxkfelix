# coding=utf-8
# 直接通过request获得源代码
# 参考网址 https://www.zhihu.com/question/358198451/answer/2647970659
# html解析：https://zhuanlan.zhihu.com/p/110789252
# 正则表达式：https://www.lmlphp.com/user/370169/article/item/10850061/
import operator
import requests
import re
import sys
import os
# 引入HTML解析器
from bs4 import BeautifulSoup
# 替换相对路径为绝对路径
def hand(url,soup):
    prefix = ''
    if isHttps(url):
        prefix = "https:"
    else:
        prefix = "http:"
    handSub(soup,prefix,'//')
    handSub(soup,getMainPage(url),'/?')# 需要注意是官网本身开头的

def handSub(soup,prefix,startChar):

    # 图片相对地址替换
    imgs = soup.find_all('img',src=re.compile("^"+startChar))
    for img in imgs:
        if isProctolStart(img['src']):
            ''
        else:
            img['src'] = prefix + img['src']
            print('after:'+img['src'])
    print('******替换img相对路径OK*********')
    # js 路径相对位置替换
    jss = soup.find_all('script', src=re.compile("^"+startChar))
    for js in jss:
        if isProctolStart(js['src']):
            ''
        else:
            js['src'] = prefix + js['src']
            print('after:' + js['src'])
    print('******替换js相对路径OK*********')
    # css 相对路径位置替换
    csss = soup.find_all('link', href=re.compile("^"+startChar))
    for css in csss:
        if isProctolStart(css['href']):
            ''
        else:
            css['href'] = prefix + css['href']
            print('after:' + css['href'])
    print('******替换css相对路径OK*********')
    # 超链接相对路径位置替换
    asss = soup.find_all('a', href=re.compile("^" + startChar))
    for ass in asss:
        if isProctolStart(ass['href']):
            ''
        else:
            ass['href'] = prefix + ass['href']
            print('after:' + ass['href'])
    print('******替换a相对路径OK*********')

# 获得指定网址的源代码
def get_source(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36'}
    # url = 'https://www.baidu.com/s?rtt=4&tn=news&word=阿里巴巴'
    #url = 'https://www.shanghai.gov.cn/'
    source = requests.get(url, headers=headers).text
    return source

# 将源代码写入指定文件
def write_file(file,soup, dir_path):
    file_path = os.path.join(dir_path, file)
    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(soup.prettify())
        f.close()


# 从url中获得网络协议：HTTP 或 HTTPS
def isHttps(url):
    if operator.contains(url,'https'):
        return True
# 根据给定的网址，获取主页地址
def getMainPage(url):
    index = url.find('/',9)
    #print(index)
    if index >-1:
        return url[0:index]
    else:
        return ''

# 根据给定的网址，获取末级页面命名
def getPageName(url):
#     index = url.rfind('/',0)
#     page_name = ''
#     if index > -1:
#         page_name = url[index+1:]
#     else:
#         page_name = 'test'
#     if page_name.find('.html')>-1:
#         return page_name
#     else:
        return "python" + ".html"
# 根据给定的网址，判定是否有协议的开头
def isProctolStart(str):
    index = str.find('http', 0)
    if index > -1:
        return True
    else:
        return False
# 主程序入口
if __name__ == '__main__':
    url = sys.argv[1]
    file = getPageName(url)
    dir_path = r'src\main\resources'
    source = get_source(url)
    soup = BeautifulSoup(source, 'lxml')
    hand(url, soup)
    write_file(file, soup, dir_path)
    print('源代码已保存至', os.path.join(dir_path, file))

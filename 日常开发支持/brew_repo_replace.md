# 查看 brew.git 当前源
cd "$(brew --repo)" && git remote -v
origin	https://github.com/Homebrew/brew.git (fetch)
origin	https://github.com/Homebrew/brew.git (push)

# 查看 homebrew-core.git 当前源
cd "$(brew --repo homebrew/core)" && git remote -v
origin	https://github.com/Homebrew/homebrew-core.git (fetch)
origin	https://github.com/Homebrew/homebrew-core.git (push)

# 修改 brew.git 为阿里源
git -C "$(brew --repo)" remote set-url origin https://mirrors.aliyun.com/homebrew/brew.git

# 修改 homebrew-core.git 为阿里源
git -C "$(brew --repo homebrew/core)" remote set-url origin https://mirrors.aliyun.com/homebrew/homebrew-core.git

# zsh 替换 brew bintray 镜像
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.aliyun.com/homebrew/homebrew-bottles' >> ~/.zshrc
source ~/.zshrc

# bash 替换 brew bintray 镜像
echo 'export HOMEBREW_BOTTLE_DOMAIN=https://mirrors.aliyun.com/homebrew/homebrew-bottles' >> ~/.bash_profile
source ~/.bash_profile

# 刷新源
brew update

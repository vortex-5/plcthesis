% mcthesis.cls
%%% ====================================================================
%%%  @LaTeX-class-file{
%%%	filename	= "mcthesis.cls",
%%%	version		= "0.1",
%%%	date		= "6 November 1997",
%%%	writtenby	= "Dennis Peters",
%%%	email		= "peters@crl.mcmaster.ca",
%%%	keywords	= "LaTeX, thesis, mcmaster, mcthesis",
%%%	docstring	= "This file provides a thesis class for 
%%%			   McMaster University, Ontario Canada"
%%% }
%%% ====================================================================
%%% ====================================================================
%%%  This class file is derrived from:
%%%	filename	= "unswthesis.cls",
%%%	version		= "1.6a",
%%%	date		= "23 October 1995",
%%%	writtenby	= "John Zaitseff",
%%%	email		= "J.Zaitseff@unsw.edu.au",
%%%	keywords	= "LaTeX, thesis, unsw, unswthesis",
%%%	docstring	= "This file provides a thesis class for the
%%%			   University of New South Wales, Australia."
%%% 
%%% ====================================================================

% This file provides a thesis class for McMaster University.
% It is meant to be
% used with LaTeX-2e, and exists because of the archaic standards
% required by universities all over the world with regards to thesis
% styles.
%
% As with all class files, this class is meant to be used as the exclusive
% LaTeX-2e class for the entire document.  It may be introduced in the
% document via:
%
%       \documentclass{mcthesis}
%
% As always, options may be placed before the {mcthesis}.  In this case,
% the following options are supported:
%
% Double/sigle spaceing options don't work, use \dsp \ssp commands
% DKP 97.08.27
%       singlespacing   - Single-space the document (ie, do not apply the
%                         thesis requirements).
%       doublespacing   - Double-space the document (actually, space it
%                         1.21 times the baseline).
%       draft           - Prints the word "DRAFT" across the front page,
%                         as well as on each page (in the footer).
%       final           - Opposite of draft: do not print "DRAFT".
%
% All of the options for the Book class are also supported, namely,
%       10pt, 11pt, 12pt             (12pt is used by default)
%       letterpaper, legalpaper,
%       executivepaper, a4paper,
%       a5paper, b5paper             (letterpaper used by default)
%       landscape                    (not used by default)
%       oneside, twoside             (oneside used by default)
%       openright, openany           (openany used by default)
%       onecolumn, twocolumn         (onecolumn used by default)
%       openbib                      (not used by default)
%       leqn, fleqn                  (not used by default)
%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{mcthesis}%
    [1997/11/06 v0.1 McMaster University Thesis Class]
\RequirePackage{fancyhdr}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   Changeable Parameters   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Change these for customisation.  To change them in a LaTeX document,
% simply use \thesisuni{xxx} and \thesisschool{xxx}.

\gdef\@thesisuni{McMaster University}
\gdef\@thesisschool{Department of Computing and Software}

% Customise for foreign languages.

\def\supervisorname{Supervisor:}
\def\assessorname{Assessor:}
\def\abstractname{Abstract}
\def\acknowledgename{Acknowledgements}

%%%%%%%%%%%%%%%%%%%%%
%   Class options   %
%%%%%%%%%%%%%%%%%%%%%

\newif\if@dspacing{}
\newif\if@thdraft{}
\newcommand\droptnm{final}

\DeclareOption{singlespacing}{\@dspacingfalse}
\DeclareOption{doublespacing}{\@dspacingtrue}
\DeclareOption{draft}{\@thdrafttrue\renewcommand\droptnm{draft}}
\DeclareOption{final}{\@thdraftfalse\renewcommand\droptnm{final}}
\DeclareOption*{\PassOptionsToClass{\CurrentOption}{book}}
\ExecuteOptions{doublespacing,final}
\ProcessOptions
\LoadClass[letterpaper,12pt,oneside,openany,\droptnm]{book}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   Draft / final options   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Put this in to put big "Draft" accross the draft versions. DKP 97.08.26
%\if@thdraft%
%    \RequirePackage[first,light]{draftcopy}%
%\fi

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   Double and single spacing   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

\newcommand{\ssp}{%
    \def\baselinestretch{1.0}%
    \large\normalsize\selectfont}
\newcommand{\dsp}{%
    \def\baselinestretch{1.33}%
    \large\normalsize\selectfont}

% Define conditional:

\if@dspacing%
    \newcommand\s@dsp{\dsp}%
\else%
    \newcommand\s@dsp{\ssp}%
\fi

% Conditional Double-spacing environment

\def\spacing{%
    \begingroup%
    \s@dsp}%
\def\endspacing{%
    \par \vskip \parskip \vskip \baselineskip%
    \endgroup%
    \vskip -\parskip%
    \vskip -\baselineskip}

% Always single-space

\def\sspacing{%
    \begingroup%
    \ssp}%
\def\endsspacing{%
    \par \vskip \parskip \vskip \baselineskip%
    \endgroup%
    \vskip -\parskip%
    \vskip -\baselineskip}

% Execute the following ONLY if double-spacing was selected.

\if@dspacing
    % The following are from "book.cls":
    \renewcommand{\mainmatter}{%
	\cleardoublepage\@mainmattertrue\pagenumbering{arabic}\s@dsp}
    \renewcommand{\backmatter}{%
	\if@openright\cleardoublepage\else\clearpage\fi%
	\@mainmatterfalse\ssp}
    \renewenvironment{figure}[1][tbp]{\@float{figure}[#1]\ssp}{\end@float\s@dsp}
    \renewenvironment{figure*}[1][tbp]{\@dblfloat{figure}[#1]\ssp}{\end@dblfloat\s@dsp}
    \renewenvironment{table}[1][tbp]{\@float{table}[#1]\ssp}{\end@float\s@dsp}
    \renewenvironment{table*}[1][tbp]{\@dblfloat{table}[#1]\ssp}{\end@dblfloat\s@dsp}

    % The following are from "latex.ltx". BUG: This is NOT single-spaced.

    \long\def\@footnotetext#1{\insert\footins{%
	\ssp%                  % Should be single-spaced, but...
	\reset@font\footnotesize
	\interlinepenalty\interfootnotelinepenalty
	\splittopskip\footnotesep
	\splitmaxdepth \dp\strutbox \floatingpenalty \@MM
	\hsize\columnwidth \@parboxrestore
	\protected@edef\@currentlabel{%
	    \csname p@footnote\endcsname\@thefnmark
	}%
	\color@begingroup
	    \@makefntext{%
		\rule\z@\footnotesep\ignorespaces#1\@finalstrut\strutbox}
	\color@endgroup}}
\fi

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   Miscellaneous Commands   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

\DeclareFontShape{OT1}{cmr}{bx}{sl}{<-> cmbxsl10}{}
\newcommand{\HUGE}{%
    \fontencoding{OT1}\fontfamily{cmr}%
    \fontseries{bx}\fontshape{sl}%
    \fontsize{36}{45}\selectfont}

\newcommand\dedicatory[1]{
\thispagestyle{plain}
\null\vfil
\vskip 60\p@
\begin{center}{\sl #1}\end{center}
\vfil\null
\cleardoublepage
}

\newcommand{\thesisuni}[1]{\gdef\@thesisuni{#1}}
\newcommand{\thesisschool}[1]{\gdef\@thesisschool{#1}}
\newcommand{\thesistitle}[1]{\gdef\@thesistitle{#1}}
\newcommand{\shorttitle}[1]{\gdef\@shorttitle{#1}}
\newcommand{\thesisauthor}[1]{\gdef\@thesisauthor{#1}}
\newcommand{\thesisauthprevdeg}[1]{\gdef\@thesisauthprevdeg{#1}}
\newcommand{\thesisauthprevdeguni}[1]{\gdef\@thesisauthprevdeguni{#1}}
\newcommand{\thesisdegree}[1]{\gdef\@thesisdegree{#1}}
\newcommand{\thesisdate}[1]{\gdef\@thesisdate{#1}}
\newcommand{\thesissupervisor}[1]{\gdef\@thesissupervisor{#1}}
\newcommand{\thesisassessor}[1]{\gdef\@thesisassessor{#1}}

\newcommand{\@thesistitle}{\@latex@error{No \noexpand\thesistitle given}\@ehc}
\newcommand{\@thesisauthor}{\@latex@error{No \noexpand\thesisauthor given}\@ehc}
\newcommand{\@thesisdegree}{\@latex@error{No \noexpand\thesisdegree given}\@ehc}
\newcommand{\@thesisdate}{\@latex@error{No \noexpand\thesisdate given}\@ehc}
\newcommand{\@thesissupervisor}{\@latex@error{No \noexpand\thesissupervisor given}\@ehc}
\newcommand{\@thesisassessor}{\@latex@error{No \noexpand\thesisassessor given}\@ehc}

\renewcommand{\title}[1]{\@latex@warning{\noexpand\title is not used with this class}}
\renewcommand{\author}[1]{\@latex@warning{\noexpand\author is not used with this class}}
\renewcommand{\date}[1]{\@latex@warning{\noexpand\date is not used with this class}}

%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   Thesis front matter   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Title page

\renewcommand{\maketitle}%
    {\begin{titlepage}%

	\null\vfil
	\begin{center}%
	    \vskip 4em%
	    {\huge \scshape \@thesistitle \par}%
	    \vskip 6em%
	    By\\
	    {\large \scshape \@thesisauthor, \@thesisauthprevdeg \par}%
	    \vskip 6em%
	    A Thesis\\
	    Submitted to the School of Graduate Studies\\
	    in partial fulfilment of the requirements
	    for the degree of \\
	    \vskip .7em%
	    {\large \@thesisdegree \par}%
	    {\large \@thesisschool \par}%
	    {\large \@thesisuni \par}%
	    \vskip 10em%
	    {\copyright\  Copyright by \@thesisauthor, \@thesisdate \par}%
	    % \vskip 8em%
	    %{\large \textsl{\supervisorname}\ \@thesissupervisor \par}%
	    %{\large \textsl{\assessorname}\ \@thesisassessor \par}%

            \newpage
     \begin{tabbing}
     MASTER OF APPLIED SCIENCE(2003) \hspace{1.5in} \=McMaster University \kill
     MASTER OF APPLIED SCIENCE(2003) \>McMaster University \\
     \>Hamilton, Ontario \\
     \end{tabbing}
     \begin{tabbing}
      NUMBER OF PAGES: \= X, A \kill
      TITLE: \> \@thesistitle \\ \hspace{1pt} \\ \hspace{1pt} \\
      AUTHOR: \> \@thesisauthor, \@thesisauthprevdeg(\@thesisauthprevdeguni)
           \\ \hspace{1pt} \\ \hspace{1pt} \\
      SUPERVISOR: \> \@thesissupervisor \\
        \hspace{1pt} \\ \hspace{1pt} \\
      NUMBER OF PAGES: \> \pageref{pre end}, \pageref{body end} \\
      \end{tabbing} 
	\end{center}\par
	%\vskip 30\p@
	%\vfil\null
    \end{titlepage}%
    \setcounter{footnote}{0}%
    \let\maketitle\relax}
    
% Pre title Page
\newcommand\makepretitle{\begin{titlepage}%
\vspace*{-2 in}
   \begin{center}%
    {\strut\@shorttitle}\par%
  \end{center}%
  \pagebreak%
  \setcounter{page}{1}%
  \end{titlepage}%
 }


% Abstract environment (not defined by "book.cls")

\newenvironment{abstract}{%
    \titlepage
    \null\vfil
    \begin{center}%
	{\Large \bfseries \abstractname}
	\@endparpenalty\@M
    \end{center}\spacing}%
    {\endspacing\par\vfil\null\endtitlepage}

% Acknowledgements environment (not defined by "book.cls")
                                                                                
\newenvironment{acknowledge}{%
    \titlepage
    \null\vfil
    \begin{center}%
        {\Large \bfseries \acknowledgename}
        \@endparpenalty\@M
    \end{center}\spacing}%
    {\endspacing\par\vfil\null\endtitlepage}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%   General Page Parameters   %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% The university requirements specify a 25mm border all around, except for
% the bottom (20mm). We actually increase this to make it more
% typographically correct. The page dimensions used were taken from "The
% LaTeX Companion", p. 85, with appropriate modifications.

%\setlength\oddsidemargin{15\p@}
%\setlength\evensidemargin{15\p@}
%\setlength\oddsidemargin{30\p@}
%\setlength\evensidemargin{30\p@}
%\setlength\topmargin{15\p@}
%\setlength\headsep{20\p@}
%\setlength\headheight{20\p@}
%%\setlength\footskip{40\p@}
%\setlength\marginparwidth{15\p@}
%\setlength\marginparsep{\z@}
%\setlength\textwidth{\paperwidth %
    %\advance\textwidth -144\p@ %
    %\advance\textwidth -\oddsidemargin %
    %\advance\textwidth -\marginparwidth %
    %\advance\textwidth -\marginparsep}
%\setlength\textheight{\paperheight %
    %\advance\textheight -140\p@ %
    %\advance\textheight -\topmargin %
    %\advance\textheight -\headheight %
    %\advance\textheight -\headsep %
    %\advance\textheight -\footskip}
\setlength\oddsidemargin{0.5in}
\setlength\evensidemargin{0in}
\setlength\topmargin{0in}
\setlength\headsep{20\p@}
\setlength\headheight{20\p@}
%\setlength\footskip{40\p@}
\setlength\marginparwidth{0in}
\setlength\marginparsep{\z@}
\setlength\textwidth{\paperwidth %
    \advance\textwidth -2in %
    \advance\textwidth -\oddsidemargin %
    \advance\textwidth -\marginparwidth %
    \advance\textwidth -\marginparsep}
\setlength\textheight{\paperheight %
    \advance\textheight -2in %
    \advance\textheight -\topmargin %
    \advance\textheight -\headheight %
    \advance\textheight -\headsep %
    \advance\textheight -\footskip}

% Page headings and footers.

\pagestyle{fancyplain}
\renewcommand{\chaptermark}[1]{\markboth{\thechapter. #1}{\thechapter. #1}}
\renewcommand{\sectionmark}[1]{}
\lhead[\fancyplain{}{\bfseries\thepage}]{\fancyplain{}{\bfseries\rightmark}}
\rhead[\fancyplain{}{\bfseries\leftmark}]{\fancyplain{}{\bfseries\thepage}}
\cfoot{\fancyplain{\textrm{\thepage}}{}}

\if@thdraft
    \rfoot[\textbf{Draft:}~\today]{}
    \lfoot[]{\textbf{Draft:}~\today}
\fi
